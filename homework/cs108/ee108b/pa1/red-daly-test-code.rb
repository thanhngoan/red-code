
# generates a long random array and ensures that it sorts consistently
# with the (known correct) ruby sorting function.  this is an ugly little
# script but it is far more robust than manual trial and error

# uses our quicksort to sort the int array
def quicksort_array(arr)
    cmd = "./spim -file quicksort.s > tmp"
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
    qsorted = quicksort_array(arr)
    if (arr.sort.inspect != qsorted.inspect)
       puts " #{qsorted.inspect}\n does not match\n #{arr.sort.inspect} "
       false
    else
       true
    end
end

def genrandarray()
  arr = Array.new
  442.times do 
    arr << (rand*19999.0).to_i
  end
  arr
end

1.times do
  exit if not test_array(genrandarray)
end

puts "tests passed."

