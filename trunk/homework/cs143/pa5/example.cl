class Animal {
   descriptor : String <- "halo";
};

class Main {
  (*
  lifeandeverything : Int <- 42;
  lessFun : Bool <- true;
  --- main () : Int { { let str : String <- "DOGGGGG" in str; 5; } }; 
  --- main () : Int { { (new IO)@IO.out_string("thing\n"); 5; } };
  (*
  main () : Int { 
  	{
  		let str : String <- "DOGGGGG\n" in (new IO)@IO.out_string(str); 
  		(new IO)@IO.out_string( (new Main)@Main.deliverAString(4) );
  		(new Main)@Main.runAllTests();
  		(new Main)@Main.print(assignee);
  		
  		---(new IO)@IO.out_string(((new Giraffe)@Giraffe.describe())@String.concat((new Giraffe)@Giraffe.describe()));
		(new IO)@IO.out_string(
			((new Giraffe)@Giraffe.selfish())@Giraffe.describe()@String.concat((new Giraffe)@Giraffe.describe()));
  		
  		if (new Main)@Main.isTwoLessThanThree() then --- 
  			(new IO)@IO.out_int(111)
  		else
  			(new IO)@IO.out_int(666)
  	    fi;
  		
  		
  		if self@Main.isThreeLessThanOrEqualToThree() then --- 
  			(new IO)@IO.out_int(111)
  		else
  			(new IO)@IO.out_int(666)
  		fi;
  		
  		(new IO)@IO.out_string("\n");
  		
  		--- test looping
  		let i : Int <- 1 in while i < 50 loop { (new IO)@IO.out_int(i); (new IO)@IO.out_string(" "); i <- i + 1;} pool;
  		
  		(new IO)@IO.out_string("\n");
  		
  		(new IO)@IO.out_int(self@Main.otherthing());
  		
  		case 5 of i : Int => 6; esac;
  		
  		5;
  	 }
  }; 
  *)
  otherthing() : Int { case 5 of i : Int => i * 111; esac };
  
  thing() : SELF_TYPE { self };
  	 
  (* Runs many tests of the COOL language.  Returns 42 on success. *)
  runAllTests() : Int {
  	{
  		(new IO)@IO.out_string( (new Main)@Main.assignTest() );
  		5;
  	}
  };
  
  isTwoLessThanThree() : Bool { 2 < 3 };
  isThreeLessThanOrEqualToThree() : Bool { 3 <= 3 };
  
  assignee : String <- "old" ;
  assignTest() : String { assignee <- "new value" };
  
  print(s : String) : Int { { (new IO)@IO.out_string(s); (new IO)@IO.out_string("\n"); 0; } };
  
  deliverAString(i : Int) : String { let stresssedLetStatement : String <- "here you go, sir!\n" in stresssedLetStatement };
  
  (*
  
  main () : Int { 6  - lifeandeverything + lifeandeverything}; 
  
  funky : String <- "((((This is a particular Value.))))\n";
  main1 () : Int { let io : IO <- new IO in { io.out_string("hello\n"); 0; } };
  --try arith operations, should equal 42
  main2 () : Bool { "There are very few" = "There are very few" }; 
  mainArith () : Int { 116 * (1 + 2 + 39 - (42 * 3 / 4) ) / 4 + ~2 }; 
  encode (i : Int) : Int { let fun : Int <- 66 in fun };
  falsify (i : Int) : Bool { true };
  addition () : Int { 4 + 8  };
  *)
  voidmain : Main;
  gir : Giraffe <- new Giraffe;
  io : IO <- new IO;
  
  main() : Int { { 
  	(io)@IO.out_string("static");
  	(gir)@Giraffe.describe() ;
   	5 / 8;
  
    }
    };
    *)
    
  gir : Giraffe <- new Giraffe;
  io : IO <- new IO;
  --bear : Int <- 5;
  
  main() : Int { {
     --if (isvoid gir) then (io)@IO.out_string("GIR IS void\n") else (io)@IO.out_string("GIR not void.. hmmm\n") fi;
  	 if (isvoid io) then true else false fi;
   
  	--if (isvoid io) then (io)@IO.out_string("IO IS void\n") else (io)@IO.out_string("io not void.. hmmm\n") fi;
  	--if (isvoid gir) then (io)@IO.out_string("it IS void\n") else (io)@IO.out_string("gir not void.. hmmm\n") fi;
  	---(gir)@Giraffe.describe() ;
   	5 / 8;
  
    }
  };
    
};



class Giraffe {
   myio : IO <- new IO;
   realdescriptor : String <- "real deal";
   fakedescriptor : String <- { (new IO)@IO.out_string(realdescriptor); realdescriptor;  };
     
   stuff() : Int { 4 };
   things() : Int { 4 };
   selfish() : Giraffe { self };
   describe() : String { self@Giraffe.talk() };
   talk() : String { fakedescriptor };
};

(** The following class exercises every type of expression. in addition to the tests performed above. **)

Class D {
	(* exercise constants and no-expressions *)
	t : Bool <- true;          ---true
	f : Bool;                  --- no expression
	i : Int <- 10;             --- integer constants
	s : String <- "KLC";       --- string constant
	o : Object <- s;        --- object identifier (s)
	
	stupid() : Bool { t };
	
	(* exercise other straightforward semantics *)
	voidworks : Bool <- isvoid t;     --- isvoid
	create_d() : D { new D };         --- new
	lte : Bool <- 3 <= 5;             --- <=
	lt : Bool <- 3 < 5;               --- <
	eq1 : Bool <- "Lisp" = "Love";    --- = (next 3 are primitive comparisons)
	eq2 : Bool <- 5 = 5;              --- =
	eq3 : Bool <- false = false;      --- =
	eq4 : Bool <- o = o;              --- =
	neg : Int <- ~i;                  --- negation
	arith() : Int { { 1 + 2; 3 - 4; 5 * 6; 7 / 8; } }; --- all arithmetic in one go
	typc : Object <- case i of 
					  thing : Int => 6;
					  str : String => "not int";
					 esac;
	letz : Object <- let x : Int <- 5 in "irrelevant";      --- let init
	letz2 : Object <- let x : Int in x;                      --- let no init
	
	iffz : Int <- if true then 5 else 7 fi;
	loopz : Object <- while true loop 4 pool;
	
	dispatch : Object <- o.type_name();                      --- standard dispatch
	dispatch2 : Object <- s@Object.type_name();               --- static dispatch
	
	parentest : Object <- ("dog");                           --- static dispatch
	
	addtwonumbers(i : Int, j : Int) : Int { i + j };             --method definition
	dispatch3(i : Int, y : Int) : Int { addtwonumbers(i + y, 5) };             --method dispatch
	
	selfish() : SELF_TYPE { self };             --SELF_TYPE in return type
	
	---thing : D <- selfish();
};

class DLesser inherits D {
	dispatch3(i : Int, y : Int) : Int { 5 }; --overridden method
	thing() : DLesser { selfish() };
};

class DLesser3 inherits D {
	dispatch3(i : Int, y : Int) : Int { 5 }; --overridden method
	thing() : DLesser3 { selfish() };
};

class Unrelated {
   doggie : D <- (new DLesser3).selfish();
};
(*
*)

(*
class C {
	a : Int;
	b : Bool;
	init(x : Int, y : Bool) : C {
           {
		a <- x;
		b <- y;
		self;
           }
	};
};
*)

