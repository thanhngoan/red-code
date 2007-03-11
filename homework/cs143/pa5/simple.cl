class Main {
  fun : Int <- 5;
  main () : Int {  { (new IO)@IO.out_int("hi there"); fun; } };
  (*
  augment (i : Int, j : Int) : Int { i };
  lessFun : Bool;
  moreFun : Bool <- lessFun;
  *)
};

