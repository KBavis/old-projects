const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const userSchema = new Schema({
  fullName: {
    type: String,
    required: true,
  },
  bio: {
    type: String,
    required: true,
  },
  profileURL: {
    type: String,
    required: true,
  },
});

module.exports = mongoose.model("Users", userSchema);
