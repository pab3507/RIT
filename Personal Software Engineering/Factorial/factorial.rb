# factorial(n) is defined as n*n-1*n-2..1 for n > 0
# factorial(n) is 1 for n=0
# Let's raise an exception if factorial is negative
# Let's raise an exception if factorial is anything but an integer

def factorial(n)
  #if statement checks if n is an instance of the Integer class
  if (!n.is_a?(Integer))
        raise 'strings are not acceptable'
  end
  #if statement checks if n is a negative number
  if n < 0
     raise 'negatives are not acceptable'
  end
  #ternary operator checks if n is equal to 0 then equal to 0, otherwise 
  n == 0 ? 1 : n*factorial(n-1)
end
