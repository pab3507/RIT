require './BasicFood.rb'

class Recipe

  def initialize( name, ingredients)
    @name = name
    @ingredients = ingredients
    @calories = 0
    @ingredients.each do |food|
	@calories = @calories + food.calories.to_i
    end
  end

  def parse()
        "#{@name},r,#{@ingredients.join(',')}"
  end

  def print()
        printf("%s %i \n", @name, @calories)
	@ingredients.each do |i|
		printf("\t")
		i.print
	end	
  end	
  
  attr_reader :name, :ingredients

end


