# Parking Lot

## Parking Lot 基本功能，可以停车取车

Given | When | Then
:---: | :---: | :---:
a car with number and the parking lot is available | park |success and return the ticket
a car without number and the parking lot is available | park | fail
a car without number and the parking lot is not available | park | fail
a car with number and the parking lot is not available | park | fail
~~the parking lot is available and no car~~ | ~~park~~ | ~~fail~~
a car with number and the parking lot is already has a car with the same number | park | fail
a matched ticket | pick | success and return the car
a matched ticket and has already picked a car | pick | fail
~~no ticket~~ | ~~pick~~ | ~~fail~~
ticket is not matched | pick | fail

## Graduate Parking Boy

Assumption: 停车小弟需要管理两个停场场A和B，A在B之前

Given | When | Then
:---: | :---: | :---:
一个有牌照的车，并且A停车场和B停车场都有空位 | 停车 | 停车成功，车停在A停车场，出票
一个有牌照的车，A停车场已满，B停车场有空位 | 停车 | 停车成功，车停在B停车场，出票
一个有牌照的车，并且A停车场和B停车场都有空位，车牌号与现有停车场里的车重复 | 停车 | 停车失败
一个无牌照的车，并且A停车场和B停车场都有空位 | 停车 | 停车失败
一个有牌照的车，并且A停车场和B停车场都满了 | 停车 | 停车失败
一张匹配的票 | 取车 | 取车成功
一张不匹配的票 | 取车 | 取车失败

## Smart Parking Boy

Assumption: 停车小弟需要管理两个停场场A和B，A在B之前，B的停车位比A多

Given | When | Then
:---: | :---: | :---:
一个有牌照的车，并且A停车场和B停车场都有空位, B的空位比A多 | 停车 | 停车成功，车停在B停车场，出票
一个有牌照的车，B停车场已满，A停车场有空位 | 停车 | 停车成功，车停在A停车场，出票
一个有牌照的车，并且A停车场和B停车场都有空位，车牌号与现有停车场里的车重复 | 停车 | 停车失败
一个无牌照的车，并且A停车场和B停车场都有空位 | 停车 | 停车失败
一个有牌照的车，并且A停车场和B停车场都满了 | 停车 | 停车失败
一张匹配的票 | 取车 | 取车成功
一张不匹配的票 | 取车 | 取车失败

## Super Parking Boy

Assumption: 停车小弟需要管理两个停场场A和B，A在B之前

Given | When | Then
:---: | :---: | :---:
一个有牌照的车，并且A停车场和B停车场都有空位, B的空置率比A高 | 停车 | 停车成功，车停在B停车场，出票
一个有牌照的车，B停车场和A停车场有1个空位 | 停车 | 停车成功，车停在A停车场，出票
一个有牌照的车，并且A停车场和B停车场都有空位，车牌号与现有停车场里的车重复 | 停车 | 停车失败
一个无牌照的车，并且A停车场和B停车场都有空位 | 停车 | 停车失败
一个有牌照的车，并且A停车场和B停车场都满了 | 停车 | 停车失败
一张匹配的票 | 取车 | 取车成功
一张不匹配的票 | 取车 | 取车失败
