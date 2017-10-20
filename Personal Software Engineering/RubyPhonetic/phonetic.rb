# Convert to/from phonetic alphabet
# YOUR NAME HERE

class Phonetic

  Letters = [
             ['A', 'ALPHA'],
             ['B', 'BRAVO'],
             ['C', 'CHARLIE'],
             ['D', 'DELTA'],
             ['E', 'ECHO'],
             ['F', 'FOXTROT'],
             ['G', 'GOLF'],
             ['H', 'HOTEL'],
             ['I', 'INDIA'],
             ['J', 'JULIET'],
             ['K', 'KILO'],
             ['L', 'LIMA'],
             ['M', 'MIKE'],
             ['N', 'NOVEMBER'],
             ['O', 'OSCAR'],
             ['P', 'PAPA'],
             ['Q', 'QUEBEC'],
             ['R', 'ROMEO'],
             ['S', 'SIERRA'],
             ['T', 'TANGO'],
             ['U', 'UNIFORM'],
             ['V', 'VICTOR'],
             ['W', 'WHISKEY'],
             ['X', 'XRAY'],
             ['Y', 'YANKEE'],
             ['Z', 'ZULU'],
             ]

  # Translate a word to its phonetic alphabet equivalent
  def self.to_phonetic(word)
    result = String.new

    if(word.kind_of?(Array))
      word = word.join('')
    end
    word.upcase!
    word.each_char do |char|
    Letters.each_index do |index|
      wordArray = Letters[index]
        if(wordArray.first == char)
          result += wordArray.last
        end
      end
      result += ' '
    end
    result.rstrip
  end

  # Translate a sequence of phonetic alphabet code words 
  # to their alphabetic equivalent
  def self.from_phonetic(str)
      result = String.new

      if(str.kind_of?(String))
        str = str.split(' ')
      end
      str.each {|val| val.upcase!}
      
      str.each do |element|
        Letters.each_index do |index|
          wordArray = Letters[index]
          if(wordArray.last == element)
            result += wordArray.first
          end
        end
      end
    result
  end

  # If the line starts with A2P, call to_phonetic on the rest of the substring
  # If the line starts with P2A, call from_phonetic on the rest of the substring
  # Otherwise, return nothing.
  def self.translate(line)
    wordArray = line.split(' ')
    if(wordArray[0] == "A2P")
      wordArray.delete_at(0)
      to_phonetic(wordArray)
    elsif(wordArray[0] == "P2A")
      wordArray.delete_at(0)
      from_phonetic(wordArray)
    end
  end

end

# This is ruby idiom that allows us to use both unit testing and command line processing
# This gets run with ruby phonetic.rb
# Does not get run when we use unit testing, e.g. ruby phonetic_test.rb
if __FILE__ == $PROGRAM_NAME
  $stdin.each do |line|
    puts Phonetic.translate(line)
  end
end
