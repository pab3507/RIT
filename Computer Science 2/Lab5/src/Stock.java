import java.util.*;

/**
 * A class that represents a stock.
 */
public class Stock implements Comparable<Stock> {
    /** the stock name */
    private String tickerSymbol;

    /** the price per share */
    private int pricePerShare;

    /** total number of shares held */
    private int sharesHeld;

    public int getSharesHeld() {
        return sharesHeld;
    }

    public int getPricePerShare() {
        return pricePerShare;
    }

    /**

     * Create a new MyStock object
     *

     * @param tickerSymbol stock name
     * @param pricePerShare price per share
     * @param sharesHeld total shares held
     */
    public Stock(String tickerSymbol, int pricePerShare, int sharesHeld) {
        this.tickerSymbol = tickerSymbol;
        this.pricePerShare = pricePerShare;
        this.sharesHeld = sharesHeld;
    }

    /**
     * Get the stock name
     * @return stock name
     */
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", pricePerShare=" + pricePerShare +
                ", sharesHeld=" + sharesHeld +
                '}';
    }

    /**
     * Two Stock objects are equal if they have the same name
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {


        if (o instanceof Stock) {
            Stock myObject = (Stock) o;
            if (tickerSymbol.equals(myObject.tickerSymbol)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.tickerSymbol.hashCode();
    }

    /**
     * The natural order comparison of MyStock object is that a MyStock
     * object with a higher price per share comes before one with a
     * lower.  If two MyStock objects have the same price, the tiebreaker
     * is alphabetical by name.
     *
     * @param o the other MyStock object to compare against
     * @return less than 0 if this object price is higher than the others,
     * 0 if they are equal, and greater than 0 if the other objects price
     * is higher than this objects
     */
    @Override
    public int compareTo(Stock o) {

        if(o.pricePerShare == this.pricePerShare){
            return o.getTickerSymbol().compareTo(tickerSymbol);
        }
        else if(o.pricePerShare > this.pricePerShare) {
            return -1;
        }
        else {
            return 1;
        }

    }

    /**
     * The main method
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        // create an assortment of MyStock objects
        Stock apple = new Stock("AAPL", 114, 5);
        Stock google = new Stock("GOOG", 768, 10);
        Stock msft = new Stock("MSFT", 31, 7);
        Stock amazon = new Stock("AMZN", 778, 4);
        Stock ebay = new Stock("EBAY", 31, 100);

        // Activity 1: make the list contains() method work
        System.out.println("Activity 1: list contains");
        ArrayList<Stock> stockList = new ArrayList<>();
        stockList.add(apple);
        stockList.add(google);
        stockList.add(msft);
        stockList.add(amazon);
        System.out.println("stockList.contains(apple)? " + stockList.contains(apple));
        System.out.println("stockList.contains(amazon)? " + stockList.contains(amazon));
        System.out.println("stockList.contains(ebay)? " + stockList.contains(ebay));

        // Activity 2: print tree stocks by decreasing price per share
        System.out.println("\nActivity 2: print tree of stocks by decreasing price per share");
        TreeSet<Stock> stockTree1 = new TreeSet<>();
        stockTree1.add(apple);
        stockTree1.add(google);
        stockTree1.add(msft);
        stockTree1.add(amazon);
        stockTree1.add(ebay);
        stockTree1.add(apple);  // intentionally add a duplicate
        for (Stock stock : stockTree1) {
            System.out.println(stock);
        }

        // Activity 3: print the stocks alphabetically by name
        System.out.println("\nActivity 3: print tree of stocks alphabetically by name");
        TreeSet<Stock> stockTree2 = new TreeSet<>(new MyStockComparator());
        stockTree2.add(apple);
        stockTree2.add(google);
        stockTree2.add(msft);
        stockTree2.add(amazon);
        stockTree2.add(ebay);
        for (Stock stock : stockTree2) {
            System.out.println(stock);
        }

        // Activity 4: print the stocks in map with a 4 rating
        System.out.println("\nActivity 4: print stocks in map with a 4 rating");
        HashMap<Stock, Integer> stockMap = new HashMap<>();
        stockMap.put(apple, 4);
        stockMap.put(google, 5);
        stockMap.put(msft, 4);
        stockMap.put(amazon, 4);
        stockMap.put(ebay, 2);

        // loop over the map and print out the matching ones here
        for (Stock stock: stockMap.keySet()){
            if (stockMap.get(stock) == 4){
                System.out.println(stock);
            }
        }
    }
    public void updatePrice(int price){

    }

    public void addToHoldings(Stock s){

    }

    public void sale(Stock s){

    }
}


/**
 * A class that overrides the natural order comparison of MyStock objects
 * and compares them alphabetically by name.
 */
class MyStockComparator implements Comparator<Stock> {
    @Override
    public int compare(Stock o1, Stock o2) {

        return o1.getTickerSymbol().compareTo(o2.getTickerSymbol());
    }
}

/* OUTPUT:
Activity 1: list contains
stockList.contains(apple)? true
stockList.contains(amazon)? true
stockList.contains(ebay)? false

Activity 2: print tree of stocks by decreasing price per share
MyStockSol{tickerSymbol='AMZN', pricePerShare=778, sharesHeld=4}
MyStockSol{tickerSymbol='GOOG', pricePerShare=768, sharesHeld=10}
MyStockSol{tickerSymbol='AAPL', pricePerShare=114, sharesHeld=5}
MyStockSol{tickerSymbol='EBAY', pricePerShare=31, sharesHeld=100}
MyStockSol{tickerSymbol='MSFT', pricePerShare=31, sharesHeld=7}

Activity 3: print tree of stocks alphabetically by name
MyStock{tickerSymbol='AAPL', pricePerShare=114, sharesHeld=5}
MyStock{tickerSymbol='AMZN', pricePerShare=778, sharesHeld=4}
MyStock{tickerSymbol='EBAY', pricePerShare=31, sharesHeld=100}
MyStock{tickerSymbol='GOOG', pricePerShare=768, sharesHeld=10}
MyStock{tickerSymbol='MSFT', pricePerShare=31, sharesHeld=7}

Activity 4: print stocks in map with a 4 rating
MyStock{tickerSymbol='MSFT', pricePerShare=31, sharesHeld=7}
MyStock{tickerSymbol='AAPL', pricePerShare=114, sharesHeld=5}
MyStock{tickerSymbol='AMZN', pricePerShare=778, sharesHeld=4}
*/
