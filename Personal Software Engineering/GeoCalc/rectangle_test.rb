require 'test/unit'
require './rectangle.rb'
require './point.rb'

class TestRectangle < Test::Unit::TestCase 

	def setup
		@rectangle = Rectangle.new(Point.new(1,2),Point.new(3,4))
	end

	def test_width
		assert_equal(2, @rectangle.width)
	end

	def test_height
		assert_equal(2, @rectangle.height)
	end

	def test_perimeter
		assert_equal(8, @rectangle.perimeter)
	end

	def test_area_method
		assert_equal(4, @rectangle.area)
	end
end
