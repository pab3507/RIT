require 'csv'
require './BasicFood'
require './Log'
require './Recipe'
require './FoodDB'

DataBase = FoodDB.new()
input = File.open('FoodDB.txt').readlines
input.each do |line|
#	line.chomp!
	separator = line.parse_csv
	if separator[1] == 'b'
		bFood = BasicFood.new(separator[0], separator[2])
		DataBase.addFood(separator[0],bFood)
#		DataBase.foods.push(bFood)
	end
	if separator[1] == 'r'
		basic = Array.new
		separator[2..-1].each do |element|
			basic.push(DataBase.getFood(element))		
			end
		bRecipe = Recipe.new(separator[0],basic)
		DataBase.addRecipe(separator[0],bRecipe)
	end
end

$stdin.each_line do|arg|
	arg.chomp!
	args = arg.split(' ')
	option = args[0]
	if args.size > 1
		item = arg[arg.index(' ')+1..-1]
        end

	case option
		when "quit"
			abort("Exiting Diet Manager")		
		when "print"
			if item == "all"
                		DataBase.printAll
		        elsif
				print(item)
        		end
		when "find"
			print(item)
			DataBase.find(item)
		when "new"
			s1 = item.split(' ')
			s2 = item.split(',')
			if s1[0] == "food"
				foodLine = s1[1].split(',')
				name = foodLine[0]
				calories = foodLine[1]
				if DataBase.contains(name)
					print("food item already in database")
					return
				else
					#print(name,calories)
					DataBase.addFood(name,calories)
				end
			elsif s1[0] == "recipe"
				if s1.size == 2
					recipeLine = s1[1].split(',')
					name = recipeLine[0]
					foods = recipeLine[1..-1]
				else
					foodItems = s2.split(' ')
					names =  foodItems[1..-1]
					name = names.join(' ')
					foods = s2[1..-1]
				end
				ingredients = Array.new
				foods.each do |f|
					if (!DataBase.contains(f))
						print("food item is not in database")
						return
					else
						ingredients.push(DataBase.getFood(f))
					end
				end
				if DataBase.contains(name)
					print("recipe is already in database")
					return
				else
					DataBase.addRecipe(name,calories)
				end
			end
					 
		when "save"

		when "log"

		when "show"
		
	end

def printInput(input)
	if input == "all"
		DataBase.print
	else 
		DataBase.printAll
	end
   end

end
