# generates a long random array and ensures that it sorts consistently
# with the (known correct) ruby sorting function.  this is an ugly little
# script but it is far more robust than manual trial and error

# uses our quicksort to sort the int array
def quicksort_array(arr)
    
    results = nil
    io = IO.popen(cmd, "w") do |pipe|
       pipe.puts arr.length.to_s
       arr.each do |val| pipe.puts val.to_s end
    end
        results = File.open("tmp") { |f|        
        f.read }

    lines = results.split(/\n/)
    lastline = lines[lines.length-1]
    lastline.split(" ").map { | item| item.to_i }
end

# tests that the given array sorts consistently
def test_array(arr)
    sum = 0
    arr.each { |i| sum = sum + i }
    File.open("tmp", "w") { |file| file.puts gen_addi_instructions(arr)  }
    puts "Actual sum: #{sum}"
    puts File.open("tmp") { |f| f.read }
#    puts io.read
#    puts gen_addi_instructions(arr)
end

def unused

    cmd = "/usr/class/ee108b/bin/spimulator.pl tmp > tmp"
    io = IO.popen(cmd, "w") do |pipe|
       pipe.puts 
     end
    results = File.open("tmp") { |f| f.read }

    cmd = "/usr/class/ee108b/bin/spimulator.pl > tmp"
    io = IO.popen(cmd, "w") do |pipe|
       pipe.puts arr.length.to_s
     end
end

def gen_addi_instructions(arr)
    str = ""
    previous_register = nil
    arr.each {|i|
       with_register = previous_register.nil? ? '$zero' : previous_register
       previous_register = '$t' + (rand*8).to_i.to_s
       str = str + 'addi ' + previous_register + ', ' + with_register + ', ' + i.to_s + "\n"
    }
    str = str + 'addi $v0, ' + previous_register + ', 0'
    str
end


def genrandarray()
  arr = Array.new
  42.times do 
    arr << (rand*19999.0 - 10000).to_i
  end
  arr
end

1.times do
  exit if not test_array(genrandarray)
end

puts "tests passed."

