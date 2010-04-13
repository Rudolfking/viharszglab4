echo "Test case 1"
java proto.Proto --no-greeting -i maps\map1.commands -o maps\map1.output
.\check_output maps\map1.output maps\map1.expect

echo "Test case 2"
java proto.Proto --no-greeting -i maps\map2.commands -o maps\map2.output
.\check_output maps\map1.output maps\map2.expect

echo "Test case 3"
java proto.Proto --no-greeting -i maps\map3.commands -o maps\map3.output
.\check_output maps\map1.output maps\map3.expect

echo "Test case 4"
java proto.Proto --no-greeting -i maps\map4.commands -o maps\map4.output
.\check_output maps\map1.output maps\map4.expect

echo "Test case 5"
java proto.Proto --no-greeting -i maps\map5.commands -o maps\map5.output
.\check_output maps\map1.output maps\map5.expect

echo "Test case 6"
java proto.Proto --no-greeting -i maps\map6.commands -o maps\map6.output
.\check_output maps\map1.output maps\map6.expect

echo "Test case 7"
java proto.Proto --no-greeting -i maps\map7.commands -o maps\map7.output
.\check_output maps\map1.output maps\map7.expect

echo "Test case 8"
java proto.Proto --no-greeting -i maps\map8.commands -o maps\map8.output
.\check_output maps\map1.output maps\map8.expect

echo "Test case 9"
java proto.Proto --no-greeting -i maps\map9.commands -o maps\map9.output
.\check_output maps\map1.output maps\map9.expect

echo "Test case 10"
java proto.Proto --no-greeting -i maps\map10.commands -o maps\map10.output
.\check_output maps\map1.output maps\map10.expect

echo "Test case 11"
java proto.Proto --no-greeting -i maps\map11.commands -o maps\map11.output
.\check_output maps\map1.output maps\map11.expect
