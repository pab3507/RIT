require 'test/unit'
require './circle.rb'
require './point.rb'

class TestCircle < Test::Unit::TestCase 

	def setup
		@circle = Circle.new(Point.new(2,3),Point.new(5,8))
	end

	def test_diameter
		assert_equal(2, @circle.diameter)
	end

	def test_circumference
		assert_equal(4, @circle.circumference)
	end

	def test_area
		assert_equal(6, @circle.area)
	end
end
