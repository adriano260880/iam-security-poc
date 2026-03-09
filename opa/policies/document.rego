package document

default allow := false

allow if {
  input.role == "admin"
}

allow if {
  input.role == "employee"
  input.action == "create"
}

allow if {
  input.role == "manager"
  input.action == "approve"
}

allow if {
    contains(input.jwt.scope, "read:client_grants")
    input.action =="read"
}