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
	
	dispatch : Object <- o.type_name();                      --- standard dispatch
	dispatch2 : Object <- s@Object.type_name();               --- static dispatch
	
	parentest : Object <- ("dog");                           --- static dispatch
	
	addtwonumbers(i : Int, j : Int) : Int { i + j };             --method definition
	dispatch3(i : Int, y : Int) : Int { addtwonumbers(i + y, 5) };             --method dispatch
	
	selfish() : SELF_TYPE { self };             --SELF_TYPE in return type
	
	thing : D <- selfish();
};

class DLesser inherits D {
	dispatch3(i : Int, y : Int) : Int { 5 }; --overridden method
	thing() : DLesser { selfish() };
};

class Main { main() : Object { 5 }; };
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

Class Main {
	main(): C {
	  (new C).init(1,true)
	};
};
*)