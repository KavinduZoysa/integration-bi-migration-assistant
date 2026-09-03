%dw 2.0
output application/json
---
do {
  var greeting = "hello"
  var target = "world"
  ---
  { message: greeting ++ " " ++ target }
}
