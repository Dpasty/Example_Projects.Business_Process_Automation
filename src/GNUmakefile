.PHONY: ex00 ex01 ex02 ex03 ex04 ex05

all: build start_prod_mode

build:
	javac -d build main/java/*.java
	
rebuild: clean build

start_prod_mode:
	java -cp build Program 

start_dev_mode:
	java -cp build Program --profile=dev

clean:
	rm -rf build
