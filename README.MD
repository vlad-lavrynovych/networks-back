Launch instructions:
1. go to src/main/resources/application.properties, configure
a) database name
b) user name
c)password for user
d) port number
2. Log into database console and restore database using dump file: src/main/resources/dump.sql
3.Run application
4. Go to API documentation web-page via this link: http://localhost:{port.number}/swagger-ui.html

Controllers:
Network controller - used to operate with network nodes and whole networks
Node controller - used to operate with separate nodes and creating network step by step.

     OPTION                           |  METHOD | MAPPING                                     | ADDITIONAL PARAMS
    - Create whole network in one step|   POST  | http://localhost:{port.number}/api/network/ |JSON in body
	- Search for Networks             |   GET   | http://localhost:{port.number}/api/network/{networkId}
	- Search for Network entries (children) | GET | http://localhost:{port.number}/api/network/{parentNetworkId}/getChildren
	- Delete network                  | DELETE  | http://localhost:{port.number}/api/network/{networkId}
	- Update whole Network            |   PUT   | http://localhost:{port.number}/api/network/{networkId} | JSON IN BODY
	- Create network step by step (ability to add child to target entry eg to Network/Substation/Transformer/Feeder)
	To create network step by step:
    1) create network node via network controller |   POST  | http://localhost:{port.number}/api/network/ |JSON in body
    2) add children nodes to network |   POST  | http://localhost:{port.number}/api/node/{parentNodeId} | JSON in body
	- Update entries (ability to update child of target entry eg: Substation/Transformer/Feeder/Resource)
	|   PUT   | http://localhost:{port.number}/api/node/{nodeId} | JSON IN BODY
	- Delete entries (ability to remove child of target entry eg: from Network/Substation/Transformer/Feeder)
	|   DELETE   | http://localhost:{port.number}/api/network/{networkId} 
	- Verifying that Network in consistent state (Hierarchy is valid, no duplication of ids)
	Implemented interface Validator, has public method validate and 2 private methods to validate hierarchy and validate uniqueness of ids
	Validator validates creating network
