#Author: Moisés Lora

$stdin.each do |line|
	#processing the line
    line.chomp!
    line.downcase!
    line.gsub!(/[^a-zA-Z\s]/, "")
    line.sub!(/^\s+/, "")
    puts line
end

