
class ExhaustiveThreadsTest
  
  def initialize
    @@plain_correct ||= `java Cracker print 1 1` 
    @@correct_output ||= @@plain_correct.split("\n").sort;
  end

  def test_correctness_with_n_threads(n)
    plain_results = `java Cracker print 1 #{n}`
    results = plain_results.split("\n").sort
    if (!@@correct_output.eql?(results))
      puts "incorrect output for n = #{n}: \n #{@@plain_correct} \n \n #{plain_results}"
    else
      puts "correct output for n = #{n}"
    end
  end
  
end

100.times do 
  40.times do |i|
    ExhaustiveThreadsTest.new().test_correctness_with_n_threads(i+1)
  end
end
