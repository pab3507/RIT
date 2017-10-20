X
class DMV_Schedule

  # Create the hash mapping line priorities ('A','B','C')
  # to an array of customers (ticket numbers).
  # Initialize ticket counter to 0.
  #
  def initialize()
   	@ticket_counter = 0
	@hash = Hash.new
	@hash['A'] = Array.new
	@hash['B'] = Array.new
	@hash['C'] = Array.new
  end
  
  # Return the current line(array) of customers(ticket numbers) 
  # for a given line priority:
  #
  def get_line( priority )  
	@hash[priority]
  end
  
  # Return the number of customers in the line for 
  # a specified priority.
  #
  def customer_count( priority )
	@hash[priority].size	
  end

  # Return the total number of customers in all lines.
  #
  def count_all
	result = 0
	result += @hash['A'].size
  	result += @hash['B'].size
	result += @hash['C'].size
	return result
  end
   
  # Generate the next sequential ticket number
  #
  def next_ticket()
	@ticket_counter+=1
	return @ticket_counter
  end
   
  # Add a new customer with a given priority  and ticket_number
  # and place them at the end of the appropriate line.
  #
  def new_customer( priority, ticket_number )  
	@hash.store(priority,ticket_number)
  end
  
  # Return the ticket_number  of the customer at the head of the line 
  # for a specified priority. 
  # The customer is removed from the line as a result of this operation. 
  # If the line is empty, return zero (0).
  #
  def serve_customer( priority )
	@hash[priority].shift
  end

  # Return the ticket_number  of the customer at the head of the line 
  # for a highest priority  that has any customers. 
  # The customer is removed from the line as a result of this operation. 
  # If all lines are empty, return zero (0).
  #
  def serve_highest_priority_customer()
   	if @hash['A'].size > 0
		return @hash['A'].shift
	elsif @hash['B'].size > 0
                return @hash['B'].shift
	elsif @hash['C'].size > 0
                return @hash['C'].shift
	else
		return 0
	end

  end
   
end
	 

  
  
  
  
