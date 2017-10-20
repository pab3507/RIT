require_relative 'dmv_schedule'
require 'test/unit'

class Test_DMV_Schedule < Test::Unit::TestCase

# Note that each test case (method) runs independently of other test cases!
# Each test starts with a new creation of DMV_Schedule.

# "Smoke Test"
# Ensure we can create an instance of DMV_Schedule
#
  def test_smoke
    sched = DMV_Schedule.new
    assert_not_nil( sched, "No instance of DMV_Schedule exists" )
  end
  
# Tests for correct initialization of DMV_Schedule 
# Requires get_line() and customer_count() methods
#
  def test_initialization_empty_arrays
    sched = DMV_Schedule.new
	assert_equal( sched.get_line('A'), [], "Line A should be an empty array")
	assert_equal( sched.get_line('B'), [], "Line B should be an empty array")
	assert_equal( sched.get_line('C'), [], "Line C should be an empty array")
  end
  
  def test_initialization_zero_customers_in_line
	sched = DMV_Schedule.new
    assert_equal( sched.customer_count('A'), 0, "Line A should have zero customers")
	assert_equal( sched.customer_count('B'), 0, "Line B should have zero customers")
	assert_equal( sched.customer_count('C'), 0, "Line C should have zero customers")
  end

# Add customers, check total customer count
#
  def test_total_customers
    sched = DMV_Schedule.new
	sched.new_customer( 'A', sched.next_ticket )
	sched.new_customer( 'B', sched.next_ticket )
	sched.new_customer( 'C', sched.next_ticket )
	assert_equal( sched.count_all(), 3, "Toal customer count should be 3")
  end
  
# Test for correct initalization and generating of next ticket number
# Requires next_ticket() method
#
  def test_next_ticket 
    sched = DMV_Schedule.new
	assert_equal( sched.next_ticket(), 1, "Next ticket not initialized / operational")
	assert_equal( sched.next_ticket(), 2, "Next ticket not initialized / operational")
  end

# Add a customer to Line A
# Requires new_customer() method
#
  def test_add_one_customer
    sched = DMV_Schedule.new
	sched.new_customer( 'A', sched.next_ticket )
	assert_equal( sched.customer_count('A'), 1, "Line A should have one customer")
	assert_equal( sched.get_line('A'), [1], "Line A should only contain ticket 1")
  end
  
# Add a customer to each line A, B, C
# Requires new_customer() method
#
  def test_add_one_customer_each_line
    sched = DMV_Schedule.new
	sched.new_customer( 'A', sched.next_ticket )
	assert_equal( sched.customer_count('A'), 1, "Line A should have one customer")
	assert_equal( sched.get_line('A'), [1], "Line A should only contain ticket 1")
	sched.new_customer( 'B', sched.next_ticket )
	assert_equal( sched.customer_count('B'), 1, "Line B should have one customer")
	assert_equal( sched.get_line('B'), [2], "Line B should only contain ticket 2")
	sched.new_customer( 'C', sched.next_ticket )
	assert_equal( sched.customer_count('C'), 1, "Line C should have one customer")
	assert_equal( sched.get_line('C'), [3], "Line C should only contain ticket 3")
  end
  
# Add multiple customers to one line.
# Requires new_customer() method
#
  def test_add_multiple_customers
    sched = DMV_Schedule.new
	sched.new_customer( 'A', sched.next_ticket )
	sched.new_customer( 'A', sched.next_ticket )
	sched.new_customer( 'A', sched.next_ticket )
	assert_equal( sched.customer_count('A'), 3, "Line A should have 3 customers")
	assert_equal( sched.get_line('A'), [1, 2, 3], "Line A should contain tickets 1,2,3")
  end

# Add multiple customers to multiple lines.
# Requires new_customer() method
#
  def test_add_multiple_customers_each_line
    sched = DMV_Schedule.new
	sched.new_customer( 'A', sched.next_ticket )
	sched.new_customer( 'A', sched.next_ticket )
	sched.new_customer( 'B', sched.next_ticket )
	sched.new_customer( 'C', sched.next_ticket )
	sched.new_customer( 'C', sched.next_ticket )
	sched.new_customer( 'A', sched.next_ticket )
	assert_equal( sched.customer_count('A'), 3, "Line A should have 3 customers")
	assert_equal( sched.get_line('A'), [1, 2, 6], "Line A should contain tickets 1,2,6")
	assert_equal( sched.customer_count('B'), 1, "Line B should have 1 customer")
	assert_equal( sched.get_line('B'), [3], "Line B should contain ticket 3")
	assert_equal( sched.customer_count('C'), 2, "Line C should have 2 customers")
	assert_equal( sched.get_line('C'), [4,5], "Line C should contain tickets 4,5")
  end
  

# Attempt to serve an empty line, should return zero.
# Requires serve_customer()
#
  def test_serve_empty_line
    sched = DMV_Schedule.new
	assert_equal(sched.serve_customer('B'), 0, "Line B should return zero (0) if empty")
	assert_equal( sched.customer_count('B'), 0, "Line B should have no customers")
	assert_equal( sched.get_line('B'), [], "Line B should be empty")
  end

# Add and server one customer
# Requires serve_customer()
#
  def test_add_serve_one_customer
    sched = DMV_Schedule.new
	sched.new_customer( 'B', sched.next_ticket )
	assert_equal(sched.serve_customer('B'), 1, "Line B should have served ticket 1")
	assert_equal( sched.customer_count('B'), 0, "Line B should now have no customers")
	assert_equal( sched.get_line('B'), [], "Line B should now be empty")
  end

# Add and server mutliple customers
# Requires serve_customer()
#
  def test_add_serve_multiple_customers
    sched = DMV_Schedule.new
	sched.new_customer( 'B', sched.next_ticket )
	sched.new_customer( 'B', sched.next_ticket )
	sched.new_customer( 'B', sched.next_ticket )
	assert_equal( sched.serve_customer('B'), 1, "Line B should have served ticket 1")
	assert_equal( sched.customer_count('B'), 2, "Line B should now have 2 customers")
	assert_equal( sched.get_line('B'), [2,3], "Line B should have tickets 2,3")
	sched.new_customer( 'B', sched.next_ticket )
	assert_equal( sched.customer_count('B'), 3, "Line B should now have 3 customers")
	assert_equal( sched.get_line('B'), [2,3,4], "Line B should have tickets 2,3,4")
	assert_equal( sched.serve_customer('B'), 2, "Line B should have served ticket 2")
	assert_equal( sched.serve_customer('B'), 3, "Line B should have served ticket 3")
	assert_equal( sched.customer_count('B'), 1, "Line B should now have 1 customer")
	assert_equal( sched.get_line('B'), [4], "Line B should have ticket 4")
  end
  
# Add and server multiple customers, multiple lines
# Requires serve_customer()
#
  def test_add_serve_multiple_customers_each_line 
    sched = DMV_Schedule.new
	sched.new_customer( 'A', sched.next_ticket )
	sched.new_customer( 'A', sched.next_ticket )
	sched.new_customer( 'B', sched.next_ticket )
	sched.new_customer( 'C', sched.next_ticket )
	sched.new_customer( 'C', sched.next_ticket )
	sched.new_customer( 'A', sched.next_ticket )
	
	assert_equal(sched.serve_customer('B'), 3, "Line B should have served ticket 3")
	assert_equal(sched.serve_customer('C'), 4, "Line C should have served ticket 4")
	assert_equal(sched.serve_customer('A'), 1, "Line A should have served ticket 1")
	assert_equal(sched.serve_customer('A'), 2, "Line A should have served ticket 2")
	
	assert_equal( sched.customer_count('A'), 1, "Line A should have 1 customer")
	assert_equal( sched.get_line('A'), [6], "Line A should contain ticket 6")
	assert_equal( sched.customer_count('B'), 0, "Line B should have 0 customers")
	assert_equal( sched.get_line('B'), [], "Line B should be empty")
	assert_equal( sched.customer_count('C'), 1, "Line C should have 1 customer")
	assert_equal( sched.get_line('C'), [5], "Line C should contain ticket 5")
	
	assert_equal( sched.count_all(), 2, "Should be 2 customers total in all lines")
  end
  
# Attempt to serve customer from highest priority line - all lines empty
# Requires serve_highest_priority_customer()
#
  def test_serve_highest_empty_line
    sched = DMV_Schedule.new
	assert_equal(sched.serve_highest_priority_customer(), 0, "Should return zero (0) if all lines empty")
  end
  
# Attempt to serve customer from highest priority line - all lines have customer
# Requires serve_highest_priority_customer()
#
  def test_serve_highest_select_A
    sched = DMV_Schedule.new
	sched.new_customer( 'B', sched.next_ticket )
	sched.new_customer( 'C', sched.next_ticket )
	sched.new_customer( 'A', sched.next_ticket )
	assert_equal(sched.serve_highest_priority_customer(), 3, "Should serve ticket 3 from A line")
  end
  
# Attempt to serve customer from highest priority line - lines B,C have customers
# Requires serve_highest_priority_customer()
#
  def test_serve_highest_select_B
    sched = DMV_Schedule.new
	sched.new_customer( 'B', sched.next_ticket )
	sched.new_customer( 'C', sched.next_ticket )
	assert_equal(sched.serve_highest_priority_customer(), 1, "Should serve ticket 1 from B line")
  end


# Attempt to serve customer from highest priority line - line C only customer
# Requires serve_highest_priority_customer()
#
  def test_serve_highest_select_C
    sched = DMV_Schedule.new
	sched.new_customer( 'C', sched.next_ticket )
	assert_equal(sched.serve_highest_priority_customer(), 1, "Should serve ticket 1 from C line")
  end

end