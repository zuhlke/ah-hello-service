


# AH Hello Service
This service responds to

    http://...:8080/greeting
    http://...:8080/greeting?name={name}

## Steps
The following steps desribe the process for building, deploying and exposing the service on GCP GKE with GCloud.

Compile the Java code

    gradle build

Create the Docker image

    docker build -t gcr.io/$DEVSHELL_PROJECT_ID/hello-service:0.0.1 .

Run the container locally

    docker run -d -p 8080:8080 gcr.io/$DEVSHELL_PROJECT_ID/hello-service:0.0.1

Upload the image to the currently set project container registry

    gcloud config set core/project aimless-hammer-development
    gcloud auth configure-docker  # if not run before?
    docker push gcr.io/$DEVSHELL_PROJECT_ID/hello-service:0.0.1

Create the cluster

    gcloud container clusters create hello-cluster\
        --metadata=disable-legacy-endpoints=true\
        --machine-type=n1-standard-1\
        --issue-client-certificate\
        --zone=europe-west1-b\
        --enable-autoupgrade\
        --no-enable-ip-alias\
        --enable-basic-auth\
        --num-nodes=2

Create the pods

    kubectl run hello-pod --image=gcr.io/$DEVSHELL_PROJECT_ID/hello-service:0.0.1 --port=8080 --replicas=2  # deprecated
    kubectl get deployments
    kubectl get nodes
    kubectl get pods
    # also useful
    kubectl describe node <node-name>
    kubectl describe pod <pod-name>

Expose the Service

    kubectl expose deployment hello-pod --type=LoadBalancer --name=hello-lb  --port=8080 --target-port=8080
    kubectl get services
