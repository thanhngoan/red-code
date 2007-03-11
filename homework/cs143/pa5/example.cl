class Animal {
   descriptor : String <- "halo";
};

class Giraffe {
   descriptor : String <- "umbrella";
   stuff() : Int { 4 };
   things() : Int { 4 };
};

class Main {
  fun : Int <- 5;
  lessFun : Bool <- true;
  funky : String <- "((((This is a particular Value.))))\n";
  main1 () : Int { let io : IO <- new IO in { io.out_string("hello\n"); 0; } };
  --try arith operations, should equal 42
  main () : Bool { "There are very few" = "There are very few" }; 
  mainArith () : Int { 116 * (1 + 2 + 39 - (42 * 3 / 4) ) / 4 + ~2 }; 
  encode (i : Int) : Int { let fun : Int <- 66 in fun };
  falsify (i : Int) : Bool { true };
  addition () : Int { 4 + 8  };
};

