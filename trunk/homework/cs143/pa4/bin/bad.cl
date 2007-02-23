class X inherits NonExistant { };

--- builtin objects cannot be inheritted
class Bad1 inherits IO {}; 
class Bad2 inherits String {}; 
class Bad3 inherits Int {}; 
class Bad4 inherits Bool { some : Int <- "thing"; }; 

--- not allowed to redefine classes
class RedefineMe {} ;
class RedefineMe {} ;

--- class C is OK
class C {
	a : Int <- false;
	b : Bool;
	init(x : Int, y : Bool) : C {
           {
		a <- x;
		b <- y;
		self;
           }
	};
};



Class AllThingsWrong {

--undefined variable zebra
	whatIsZebra : Object <- zebra;
};

Class WouldbeMain {
	main():C {
	 {
	  (new C).init(1,1);
	  (new C).init(1,true,3);
	  (new C).iinit(1,true);
	  (new C);
	 }
	};
};

