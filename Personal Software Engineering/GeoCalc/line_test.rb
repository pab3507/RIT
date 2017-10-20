require 'test/unit'
require './line.rb'
require './point.rb'

class TestLine < Test::Unit::TestCase 

	def setup
		@line = Line.new(Point.new(2,5),Point.new(6,5))
	end

	def test_length
		assert_equal(4,@line.length)
	end
end

