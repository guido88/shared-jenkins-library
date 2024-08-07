Shared Jenkins Library
---

This is a shared jenkins library, exposing functions to be used as part of Jenkins pipeline. As they are not specific to a pipeline , can be reused for another job.

1. build a java maven app
2. build a docker image
3. Push the image on private repo
4. Deploy on ec2 instance using docker-compose file and custom script to run it
5. Commit to app repository the newly created version

*hardcoded values to be removed, to make library reusable
