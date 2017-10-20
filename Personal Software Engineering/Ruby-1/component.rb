
# Component Functions

# Given a string of exactly three fields separated by semi-colons (';'),
# split the string into an array of three strings and return this final array.
# NOTE: You may assume the string consists of exactly three fields, the first
# of which represents a legal number.
# A typical string would be '7.1;inches;feet' (without the apostrophe marks).
# You return an array of three strings ['7.1','inches','feet']

#I created a helper function to check if the string is a valid float, and defined the function as an extension of the String class
#This allows for it to be used in other functions
class String
  def valid_float?
    true if Float(self) rescue false
  end
end

#This splits the line array by the semicolon character
def parse( line )
  line.split(';')
end

# Convert the text_value passed to a floating point number.
# HOWEVER -- if the text_value string is an empty string then return 0.0 as the value

#Used a Ruby Ternary Operator as it was simpler than needing an if/else for this case
#If the string is not empty, it executes the string to float conversion
#Remaining else conditions just returns 0.0
def convert_float( text_value )
  (!text_value.empty?) ? text_value.to_f : 0.0
end

# If inches is a number then convert that number to feet (divide by 12.0).
# If it is not a number then return 0.0

#Used again a ternary operator, that checks if inches is a valid float, if it is, it executes the conversion, else return 0.0
def convert_inches_to_feet( inches )
    inches.valid_float? ? (inches.to_f/12.0) : 0.0
end

# Sum the array of numbers passed and return that sum  

#Inject sums the array with the binary operator :+, the 0 base case is needed otherwise nil will be returned on empty arrays
def sum_array( numbers )
    numbers.inject(0, :+)
end