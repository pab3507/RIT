require 'test/unit'
require_relative 'component'   

class ComponentTest < Test::Unit::TestCase 

  def close_enough(expected, actual, epsilon = 0.001)
    return (expected - actual).abs <= epsilon
  end

  ### Tests of parse ###

  def test_001_parse
    results = parse( '7.1;inches;feet' )
	assert( '7.1', results[0] )
	assert( 'inches', results[1] )
	assert( 'feet', results[2] )
  end

  #test normal case
  def test_002_convert_float1
	result = convert_float( '1.5')
	assert close_enough( 1.5, result )
  end

  #test empty string case
  def test_002_convert_float2
	result = convert_float( '')
	assert close_enough( 0.0, result )
  end

  def test_003_convert_inches_to_feet
    result = convert_inches_to_feet( 'bogus' )
	assert close_enough( 0.0, result )
  end
  
  def test_004_sum_array
    result = sum_array( [0.0] )
	assert close_enough( 0.0, result )
  end

  def test_005_sum_array2
    result = sum_array( [1.1, -1.2, 3.3] )
	assert close_enough( 3.2, result )
  end


  # Create a unit test that converts 36 inches to 3 feet
  def test_006_convert_inches_to_feet
    result = convert_inches_to_feet('36.0')
  assert close_enough(3.0, result)
  end

end
