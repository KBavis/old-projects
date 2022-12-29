var express = require("express");
var router = express.Router();
const app = express();
const path = require("path");

var dir = "D:\\Code\\Workplace\\Express-React-Application\\client";

router.get("/add/:firstNumber/and/:secondNumber", (req, res) => {
  console.log(req.params.firstNumber + req.params.secondNumber);
  let firstNo = parseInt(req.params.firstNumber),
    secondNo = parseInt(req.params.secondNumber);
  return res.json({ Addition: firstNo + secondNo });
});

// router.use(express.static(path.join(dir, "build")));
// router.get("/", (req, res) => {
//   res.sendFile(path.resolve(dir, "public", "index.html"));
// });

module.exports = router;
