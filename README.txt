/**********************************************
 * Please DO NOT MODIFY the format of this file
 **********************************************/

/*************************
 * Team Info & Time spent
 *************************/

	Name1: Michael Elgart
	NetId1: mje15
	Time spent: too many

	Name2: Jennifer Mercado
	NetId2: jdm45
	Time spent: not as many as Michael 

/******************
 * Files to submit
 ******************/

	lab3.jar // An executable jar including all the source files and test cases.
	README	// This file filled with the lab implementation details
	Elevator.input // You can submit a sample input and log file
        Elevator.log   // corresponding to the input but Elevator.log should be 
		       // auto-generated on execution of jar file

/************************
 * Implementation details
 *************************/

/* 
 * This section should contain the implementation details and a overview of the
 * results. 

 * You are required to provide a good README document including the
 * implementation details. In particular, you can use pseudocode to describe
 * your implementation details where necessary. However that does not mean to
 * copy/paste your C code. Specifically, explain the synchronization primities
 * used in implmenting the elevator, scheduling choices used, how the capacity
 * constraint is handled, and how the mulitple elevators are supported. Also,
 * explain how to run the test cases for your EventBarrier and Elevator and how
 * to interpret the results. Check the README.OUTPUTFORMAT for the acceptable
 * input/output format for the elevator. Expect the design and implementation
 * details to be at most 2-3 pages.  A plain textfile is encouraged. However, a
 * pdf is acceptable.  No other forms are permitted.

 * In case of lab is limited in some functionality, you should provide the
 * details to maximize your partial credit.  
 * */

The purpose of this lab is to develop EventBarrier and Elevator classes that utilize 
(and correctly so) synchronization techniques in Java. EventBarrier allows a group of 
threads to respond to an event in a synchronized fashion, and Elevator simulates a 
building with a set number of floors, elevators and riders.

EventBarrier was implemented using synchronized methods and the condition variable "isSignaled." 
The "arrive" method checks to see if the barrier is raised; if not, it waits until it 
has raised. The "raise" method raises the barrier, waits until all waiting threads have 
passed through the barrier, and then closes. The "complete" method notifies the barrier 
that all of the waiting threads have passed through the barrier and are ready to begin 
handling the next event. The method "waiter" returns the number of threads waiting at 
the barrier. The GateKeeper and Minstrel (and EventBarrierTester) classes are used for 
testing. The elevator simulator was also used for testing.

The elevator controller was implemented using the EventBarriers created in Part 1 of the
lab. The given AbstractElevator and AbstractBuilding classes were extended (to create the
aptly named Elevator and Building classes), and a Rider class was made as well. Elevators
and Riders are runnables, as they are the actual threads that will be running. Building 
is a regular class, and ElevatorTester is executable with a text input file as a parameter.

The interactions between riders, buildings, and elevators are handled by two 1D arrays of 
EventBarriers and one 2D array of EventBarriers. There is are two arrays of exit barriers 
per floor, one that handles CallUp events and one that handles CallDown events. When a rider
calls up or down, it "arrives" at that event barrier and waits. The 2D array stores a list
of event barriers for each elevator at each floor, so each rider that enters the elevator 
will "arrive" at the event barrier for its desired exit floor and will exit when it reaches 
that floor and the barrier is raised (the elevator door is open).

The elevators currently either start at the top or bottom floor, depending on whether their 
ID is odd or even respectively. They go all the way from one end of the building to the other,
opening at each floor to see if any riders are waiting (and if there are none, closing 
immediately). The elevator event barrier is raised first, so the first riders to move are 
those exiting the elevator. Once they have all exited, that raise call returns and the event 
barrier for the floor's up or down call (whichever way the elevator is going) is raised. The 
riders waiting to go in the elevator's current direction are allowed to enter, and that barrier
is then lowered. 
riders
waiting to enter the elevator are 

At the moment, our files are not in a jar file, and we recognize that. There's not a whole lot to execute at the moment. The final submission will be in an executable jar file.

/************************
 * Feedback on the lab
 ************************/

"We should do it in Erlang" - Michael 

/************************
 * References
 ************************/

StackOverflow (for "cannot find main class" error, because Eclipse decided it wanted
to be sassy)
