# Aquila Server

The backend for the Aquila system that manages pre-award/post-award workflows
for UAS at CSULA.

## First Time Setup
1. Clone the project from its GitHub repository
2. In Eclipse, select *File* -> *Import* ... -> *Existing Maven Projects* to
	import the project into Eclipse.
3. Run SchemaGeneration.java
4. Open aquila-tables.ddl found in `src/main/scripts/` and copy the schema
5. Create database in MySQL Workbench
	* Create new query
	* Type in 
		* `CREATE schema aquila;`
		* `USE aquila;`
	* Paste the aquila-tables.ddl
	* Run the query 
6. Run AquilaServerApplication.java as java application
7. Begin testing requests

## Test procedure
If you have made any modifications to the models, you need to re-create the schema.
1. Run SchemaGeneration.java
4. Open aquila-tables.ddl found in `src/main/scripts/` and copy the schema
5. Create database in MySQL Workbench
	* Create new query
	* Type in 
		* `DROP schema aquila;`
		* `CREATE schema aquila;`
		* `USE aquila;`
	* Paste the aquila-tables.ddl
	* Run the query 
6. Run AquilaServerApplication.java as java application
7. Begin testing requests