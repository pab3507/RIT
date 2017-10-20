# Ruby Diet Manager
# L. Kiser
# Sept. 18, 2015

class BasicFood

  def initialize( name, calories )
    @name = name
    @calories = calories
  end
  
  def print()
	printf("%s %i \n", @name, @calories)
  end

  def parse()
	"#{@name},b,#{@calories}"
  end
  attr_reader :name, :calories
end
