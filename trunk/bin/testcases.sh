echo "Running prototype test cases..."

for testcase in `seq 1 11`
do
	echo "Test case ${testcase}"
	java proto/Proto --no-greeting -i "./maps/map${testcase}.commands" -o "./maps/map${testcase}.output"
	./check_output "./maps/map${testcase}.output" "./maps/map${testcase}.expect"
done

