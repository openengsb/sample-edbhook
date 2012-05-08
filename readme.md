This project is a sample implementation for the usage of EDB-Hooks that use the similarity service.


# Hooks

## EDBPreCommitHook
The EDBPreCommitHook is executed before the commit starts. The essential similarity analysis queries are executed and if there is one or more conflicts, this hooks throws an EDBException, that is handled by the EDB Service.

## EDBErrorHook
The EDBErrorHook is called if an EDBException occurs. This EDBException can be either thrown by the EBDPreCommitHook or by the internal conflict detection mechanism of the EDB.

## EDBPostCommitHook:
The EDBPostCommitHook updates the index with new data that is written into the EDB. It is called after the commit to the EDB was successful.

# Index implementations
This project contains two sample index implementations with a specific index that is used for advanced conflict detection or similarity analysis.




