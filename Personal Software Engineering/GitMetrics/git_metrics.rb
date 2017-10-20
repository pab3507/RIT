# Script that obtains various git metrics from a basic git log file
require 'set'
require 'date'

# Given an array of git log lines, count the number of commits in the log
def num_commits(lines)
	commitsArray = Array.new
	index = -1
	lines.each do |x|
        if x.start_with?("commit ")
            index += 1
            commitsArray[index] = x
        end
    end
	index += 1
	index
end

# Given an array of git log lines, count the number of different authors
#   (Don't double-count!)
# You may assume that emails make a developer unique, that is, if a developer
# has two different emails they are counted as two different people.
def num_developers(lines)
	authorsArray = Array.new
	diffArray = Array.new
	res = 0
	index = -1
	count = 0
	lines.each do |x|
    		if x.start_with?("Author: ")
        		index +=1
        		authorsArray[index] = x
    		end
    	end
	diffArray = authorsArray.uniq!
	res = diffArray.size
	res
end

# Given an array of Git log lines, compute the number of days this was in development
# Note: you cannot assume any order of commits (e.g. you cannot assume that the newest commit is first).
def days_of_development(lines)
	datesArray = Array.new
	days = 0
	index = -1
	lines.each do |x|
    		if x.start_with?("Date: ")
        		datesArray.push(DateTime.parse(x[8..-1]))
			datesArray.sort!
        		days = (datesArray[-1] - datesArray[0]).to_i
			days += 1
    		end
    	end
	days
end

# This is a ruby idiom that allows us to use both unit testing and command line processing
# Does not get run when we use unit testing, e.g. ruby test_git_metrics.rb
# These commands will invoke this code with our test data: 
#    ruby git_metrics.rb < ruby-progressbar-short.txt
#    ruby git_metrics.rb < ruby-progressbar-full.txt
if __FILE__ == $PROGRAM_NAME
  lines = []
  $stdin.each { |line| lines << line }
  puts "Number of commits: #{num_commits lines}"
  puts "Number of developers: #{num_developers lines}"
  puts "Number of days in development: #{days_of_development lines}"
end

