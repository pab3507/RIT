import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 *
 *  * Implementation of the Brokerage class.  In this simplified simulation
 * the brokerage will manage a single client's investments.  It will
 * also track the movement of the market as a whole.
 *
 *
 * Implementation of the best brokerage class ever. It's so litttttt. #itwasmybirthdaythispastweekend
 * #don't wanna comment
 * #birthday gift????
 */
public class Brokerage {

    /**
     * Constructor. Initializes the investor and the market as a whole. In this simplified simulation there is just a
     * single investor and the whole market is tracked by the brokerage.
     */

    private List<Stock> holding;

    /* Client's remaining cash */
    private int cash;

    /* Map containing stocks available and their current price per share.
     */
    private Map<String, Integer> market =
            new HashMap<String, Integer>();

    /**
     * Constructor.  Initializes the investor and the market as a whole.
     * In this simplified simulation there is just a single investor and the
     * whole market is tracked by the brokerage.
     * @param initialInvestment initial investment
     */
    public Brokerage(int initialInvestment) {
        holding = new ArrayList<Stock>();
        cash = initialInvestment;
		/* initialize the market */
        market.put("GOOG", 1183);
        market.put("AMZN", 360);
        market.put("AAPL", 532);
        market.put("YHOO", 38);
        market.put("MSFT", 40);
        market.put("EBAY", 57);
    }

    /**
     * Add to Investor's holding.  This function should error-check to
     * ensure the ticker symbol exists, the number of shares requested
     * is a positive value, and that the client has sufficient funds.
     * @param tickerSymbol the particular stock to buy
     * @param shares the number of shares requested
     * @return true if transaction is completed.  False otherwise.
     */
    public boolean increaseHolding(String tickerSymbol, int shares) {
		/* make sure
		 * 1) ticker symbol exists
		 * 2) shares is > 0
		 * 3) client has sufficient funds for purchase
		 *
		 * If new stock, create new object.  Else, add to existing holding.
		 */

        tickerSymbol = tickerSymbol.toUpperCase();

		/* invalid ticker symbol */
        if(!market.containsKey(tickerSymbol))
            return false;
		/* invalid number of shares */
        if(shares <= 0)
            return false;
        if(cash < market.get(tickerSymbol) * shares)
            return false;

		/* otherwise, we can process this transaction.  Either introduce
		 * a new Stock object or add to existing.
		 */
        Stock newStock = new Stock(tickerSymbol, market.get(tickerSymbol), shares);
        if(holding.contains(newStock)) {
            Stock existingStock = holding.get(holding.indexOf(newStock));
            existingStock.addToHoldings(newStock);
        }
        else {
            holding.add(newStock);
        }

        // update remaining cash
        cash -= market.get(tickerSymbol) * shares;

        return true;
    }

    /**
     * Reduce Investor's holding.  This function should error-check to
     * ensure the ticker symbol exists, and the number of shares to reduce
     * is a positive value no greater than the number currently held.
     * @param tickerSymbol the particular stock to sell
     * @param shares the number of shares to sell
     * @return true if transaction is completed.  False otherwise.
     */
    public boolean reduceHolding(String tickerSymbol, int shares) {
		/* make sure
		 * 1) ticker symbol exists
		 * 2) shares is > 0 and <= number of shares held
		 *
		 * If this reduces a holding to zero shares, remove from portfolio.
		 */

        tickerSymbol = tickerSymbol.toUpperCase();
		/* invalid ticker symbol */
        if(!market.containsKey(tickerSymbol))
            return false;

		/* go ahead and create the Stock object to use as reference */
        Stock reduceStock = new Stock(tickerSymbol, market.get(tickerSymbol), shares);

		/* investor doesn't own this stock */
        if(holding.contains(reduceStock) == false)
            return false;

		/* at this point grab this stock for use, even though still might be
		 * invalid request
		 */
        Stock existingStock = holding.get(holding.indexOf(reduceStock));

		/* invalid number of shares */
        if((shares <= 0) || (shares > existingStock.getSharesHeld()))
            return false;

		/* otherwise, we can process this transaction.  Either just reduce
		 * the number of shares or remove completely.
		 */
        if(shares == existingStock.getSharesHeld()) {
            holding.remove(existingStock);
        }
        else {
            existingStock.sale(reduceStock);
        }

        // update remaining cash
        cash += market.get(tickerSymbol) * shares;

        return true;
    }

    /**
     * Generates a string to represent the investor's portfolio.  Can be
     * requested in alphabetical order, or in decreasing order of the value of the
     * holdings (shares * price per share).
     * @param choice "N" for by name, "V" for by value
     * @return String representing the portfolio.  This string must
     * include the name, number of shares, price per share, and total
     * value for each stock in the portfolio.  The entries must be
     * sorted according to the input request.
     */
    public String accessPortfolio(String choice) {
        if(choice.equals("N")) {
            // sort by name ( the compareTo method )
            Collections.sort(holding);
        }
        else {
            // sort using ByValue Comparator method
            ByValue bv = new ByValue();
            Collections.sort(holding, bv);
            Collections.reverse(holding);

        }
        // now build up the string
        String output = "";
        output += "CURRENT PORTFOLIO\n";
        output += "Cash Available: " + String.valueOf(cash) + "\n";
        output += "SYMBOL SHARES PRICE TOTAL VALUE\n";
        output += "===============================\n";
        for (Stock s : holding) {
            output += String.format("%6s", s.getTickerSymbol());
            output += String.format("%6d", s.getSharesHeld());
            output += String.format("%7d", s.getPricePerShare());
            output += String.format("%12d", s.getPricePerShare() * s.getSharesHeld());
            output += "\n";
        }

        return output;
    }

    /**
     * Update the price per share of each stock using a random value to
     * determine the change.  A multiplier is applied to the stock price and the
     * result is rounded to the nearest integer.  A minimum price of $1 is
     * required. (For the given inputs, this constraint will always hold without
     * check). This method can also be used to update the price of a stock
     * inside any stock object that contains that information.
     * @return A string "ticker" that indicates the ticker symbols and their price.
     */
    public String tickerUpdate() {

        String output = "";

        for(String str : market.keySet()) {
            int currVal = market.get(str);
            int num = (int)(Math.random() * 5);
            int newVal;
            switch(num) {
                case 0:
                    newVal = (int)(currVal * .9 + 0.5);
                    break;
                case 1:
                    newVal = (int)(currVal * .95 + 0.5);
                    break;
                case 2:
                    newVal = currVal;
                    break;
                case 3:
                    newVal = (int)(currVal * 1.1 + 0.5);
                    break;
                case 4:
                default:
                    newVal = (int)(currVal * 1.2 + 0.5);
                    break;
            }

            market.put(str,  newVal);
            output += str + " " + String.valueOf(newVal) + "      ";
        }

        // if your simulation uses Stock objects that keep track
        // of the market price of the stock, you may choose to update
        // those Stock objects here.
        for(Stock s : holding) {
            s.updatePrice(market.get(s.getTickerSymbol()));
        }

        return output;
    }

    /**
     * Sell all remaining stocks in the portfolio.
     * @return the cash value of the portfolio.
     */
    public int closeAccount() {
        while(holding.size() > 0) {
            Stock s = holding.remove(0);
            cash += market.get(s.getTickerSymbol()) * s.getSharesHeld();
        }

        return cash;
    }
}


class ByValue implements Comparator<Stock> {

    /*
     * ByValue class provides an alternative method for sorting Stocks,
     * using the value of each holding (shares * price per share).
     */
    @Override
    public int compare(Stock s1, Stock s2) {

        int val1 = s1.getSharesHeld() * s1.getPricePerShare();
        int val2 = s2.getSharesHeld() * s2.getPricePerShare();

        if(val1 < val2)
            return -1;
        else if(val1 == val2)
            return 0;
        else
            return 1;
    }

}
