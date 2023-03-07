# DaofabAssignment
Project for DAOFAB assignment

Steps to run assignment project.

1. Clone this respository : git clone https://github.com/pritom0057/DaofabAssignment.git
2. Go to root directory of the project.
3. Build the project : mvn clean install
4. Run the project : java -jar ./target/daofab-0.0.1-SNAPSHOT.jar
5. Varify assignment tasks.

API to fetch parent details:- 

First Task Rest Api : GET : http://127.0.0.1:8080/daofab-service/api/internal/v1/daofab-parent-transaction/data?page_no=0

Sample response :
[
   {
      "id":1,
      "sender":"ABC",
      "receiver":"XYZ",
      "totalAmount":200,
      "totalPaidAmount":100
   },
   {
      "id":2,
      "sender":"XYZ",
      "receiver":"MNP",
      "totalAmount":100,
      "totalPaidAmount":100
   }
]


API to fetch the CHILD detail by parent ID

Second Task Rest Api : http://127.0.0.1:8080/daofab-service/api/internal/v1/daofab-child-transaction/data?parent_id=1

Sample response for parentId=1 :
[
   {
      "id":1,
      "paidAmount":10,
      "sender":"ABC",
      "receiver":"XYZ",
      "totalAmount":200
   },
   {
      "id":2,
      "paidAmount":50,
      "sender":"ABC",
      "receiver":"XYZ",
      "totalAmount":200
   },
   {
      "id":3,
      "paidAmount":40,
      "sender":"ABC",
      "receiver":"XYZ",
      "totalAmount":200
   }
]



Author
Sarwar Siddique (sarwar.firebase1@gmail.com)
