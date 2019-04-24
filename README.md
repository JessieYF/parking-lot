# Parking Lot

## Parking Lot 基本功能，可以停车取车

Given | When | Then
:---: | :---: | :---:
a car with number and the parking lot is available | park |success and return the ticket
a car without number and the parking lot is available | park | fail
a car without number and the parking lot is not available | park | fail
a car with number and the parking lot is not available | park | fail
the parking lot is available and no car | park | fail
a matched ticket | pick | success and return the car
no ticket | pick | fail
ticket is not matched | pick | fail

