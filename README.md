## How to Run the Programs:

First, launch the Server by right-clicking on its file and selecting “Run”. Repeat this process for the Client. It is crucial that the Server is started before the Client to ensure proper connectivity and functionality.

## Description of the Problem:

The problem centers on modeling zones of activity in the Madrid and Barcelona airports, along with the connections between them. The threads to simulate these activities are buses and airplanes that are generated simultaneously throughout the start of the program and continue their operations indefinitely. The buses’ purpose is to transport passengers between airports, while the airplanes have to access each of the zones. Some of these zones have a limited capacity or have sections that can only be entered or exited by one airplane at a time, requiring mutual exclusion mechanisms and data structures for synchronized communication to model realistic interactions within the airport ecosystem.

Other than the airplane and bus threads, the program’s primary actors and their quantities are as follows:

1. **AirplaneCreator (1):** Generates the 8000 airplanes with their respective properties and unique identifiers in a staggered manner.
2. **BusCreator (1):** Generates the 4000 buses with their respective properties and unique identifiers in a staggered manner.
3. **Airports (2):** Comprise zones—hangars, maintenance halls, boarding gates, runways, parking areas, and taxi areas—accessed during the airplane and bus cycles. 
   - **Hangar:** Unlimited capacity
   - **Maintenance Hall:** Maximum capacity of 20. FIFO entrance from parking.
   - **Boarding Gates:** Maximum capacity of 6. FIFO entrance from parking.
   - **Runways:** Maximum capacity of 4.
   - **Parking area:** Unlimited capacity.
   - **Taxi area:** Unlimited capacity.
4. **Passengers:** While not modeled as threads, the passengers’ movement is tracked in the simulation during transfer between buses, airplanes, and airports.
5. **Airways (2):** Connect the airports to simulate airplane flights.
6. **Downtown bus stops (2):** Interaction points for buses to pick up and drop off passengers.
7. **Log (1):** Records all activities in the simulation in real-time.
8. **Graphical User Interface (1):** Interface displaying real-time information about airport and connection operations, including the number of passengers, airplane statuses, and the ability to pause/resume the system.
9. **Distributed programming:** To address this requirement, Java RMI was used due to its straightforward integration within Java environments. This approach allowed the implementation of a client capable of interacting with remote objects. Through a defined remote interface, the client executes methods remotely, facilitating the real-time display of airport information on the interface.
