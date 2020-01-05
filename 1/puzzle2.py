fp = open('input.txt','r')
sum = 0
line = fp.readline()
while line:
	current = int(line)
	newFuelReq = (current / 3) - 2
	currentSum = newFuelReq
	while newFuelReq > 0:
		newFuelReq = (newFuelReq / 3) - 2
		if (newFuelReq > 0):
			currentSum += newFuelReq
	sum += currentSum
	line = fp.readline()
print(sum)