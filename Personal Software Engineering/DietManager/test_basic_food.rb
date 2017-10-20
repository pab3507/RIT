# Ruby Diet Manager
# L. Kiser
# Sept. 18, 2015

require 'test/unit'
require './BasicFood'

class TestBasicFood < Test::Unit::TestCase
  #def setup
  #end

  # def teardown
  # end

  def test1
    food = BasicFood.new('Avocado', 225)

    assert( food.calories == 225, 'Failure in BasicFood calorie accessor' )
    assert( food.name == 'Avocado', 'Failure in BasicFood name accessor' )
    assert( food.name == 'wrongname', 'This test should fail because the food.name is: ' + food.name )
  end
end