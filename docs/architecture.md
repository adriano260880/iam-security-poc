sudo docker run -p 8181:8181 \
  -v $(pwd)/opa/policies:/policies \
  openpolicyagent/opa run \
  --server \
  --addr 0.0.0.0:8181 \
  --log-level debug \
  /policies


curl --request POST \
--url https://dev-whrzfyripp481nj5.us.auth0.com/oauth/token \
--header 'content-type: application/json' \
--data '{
  "client_id": "client_id",
  "client_secret": "client_secret",
  "audience": "https://dev-whrzfyripp481nj5.us.auth0.com/api/v2/",
  "grant_type": "client_credentials"
}'