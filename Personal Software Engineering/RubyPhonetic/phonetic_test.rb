require_relative 'phonetic'
require 'test/unit'

class PhoneticTest < Test::Unit::TestCase

  def test_line_rit_to_phonetic
    assert_equal 'ROMEO INDIA TANGO', Phonetic.translate('A2P RIT')
  end
  
  def test_line_moises_to_phonetic
    assert_equal 'MIKE OSCAR INDIA SIERRA ECHO SIERRA', Phonetic.translate('A2P MOISES')
    end
  def test_line_moises_from_phonetic
    assert_equal 'MOISES', Phonetic.translate('P2A MIKE OSCAR INDIA SIERRA ECHO SIERRA')  
  end
  def test_line_swen_to_phonetic
    assert_equal 'SIERRA WHISKEY ECHO NOVEMBER', Phonetic.translate('A2P SWEN')
    end
  def test_line_swen_from_phonetic
    assert_equal 'SWEN', Phonetic.translate('P2A SIERRA WHISKEY ECHO NOVEMBER')  
  end
  #Remove this line and place more tests here

end
