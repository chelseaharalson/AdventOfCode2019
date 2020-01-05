fp = open('input.txt','r')
sum = 0
line = fp.readline()
while line:
	current = int(line)
	sum += (current / 3) - 2
	line = fp.readline()
print(sum)