class FoodDB

  def initialize()
    @foods = Hash.new
    @recipes = Hash.new
  end

  attr_accessor :foods, :recipes
 
  def addFood(key, value)
	@foods[key] = value
  end	

  def addRecipe(key,value)
	@recipes[key] = value
  end

  def getFood(key)
 	@foods[key]
  end

  def getRecipe(key)
	@recipes[key]
  end
  def printAll()
	 @foods.each do |key,value|
		value.print
	 end
         @recipes.each do |key,value|
                value.print
         end
  end
  
  def contains(item)
	 @foods.key?(item) || @recipes.key?(item)
  end

  def find(input)
	input.downcase!
	@foods.each do |key,value|
		if (key.downcase.includes? input)
			value.print
		end
	end
	@recipes.each do |key,value|
                if (key.downcase.includes? input)
                        value.print
		end
	end
  end

end


