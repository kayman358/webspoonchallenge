# webspoonchallenge

I created a hashmap to save all the objects that are being created using the post request, this is saved in form of a key value pair. The name of the object (String) serves as the key
while the Object serves as the value. When a payload is sent to the post endpoint, it creates an object using the model class' public setter methods. After successful creation, the 
201 created status code is returned and the object is returned in a json format.

Objects can also be searched from the map in constant time O(1), using the name of the object which is passed as a path variable. The map does a check of all the objects and returns the 
one that its key matches the name supplied. This also returns a 200 OK http status code.

The like functionality was built by extending the property of the model object to accommodate the like property which was declared as a long variable. On each access to this endpoint 
the name is checked to exist in the map, if this is true, it increases the count of likes for this object by 1 on each call and extends the expiry time by 30 seconds.

When the object is not found a 404 error code is returned.
