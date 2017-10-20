#Author: Moisés Lora

# Creating a new map
bag = Hash.new(0)


$stdin.each do |line|
	
        #processing the line
        line.chomp!
        line.downcase!
        line.gsub!(/[^a-zA-Z\s]/, "")
        line.sub!(/^\s+/, "")
        
	#splitting line
	line_array = line.split(/ +/)
	#accounting for each word
	line_array.each do |word|
		bag[word] += 1 
	end
end


bag.each { |key, value| puts "#{key} has #{value}" }
result_bag =  bag.select{ |key, value| value >= 2 }
result_array = Array.new
result_bag.each do |key, value|
	 pair = [key,value]
	 result_array << pair
end
puts "\nWords with at least two occurrences"
result_array.each do |pair|
	puts "#{pair[0]} has #{pair[1]}"

end
 		


