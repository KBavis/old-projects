import "bootstrap/dist/css/bootstrap.css";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import "../App.css";
import Image from "react-bootstrap/Image";

const UserDetails = ({ user }) => {
  return (
    <Container fluid>
      <Row fluid>
        <Col md="2">
          <Image
            className="profile_pic"
            src={user.profileURL}
            width="300px"
            alt="Profile picture"
          ></Image>
        </Col>
        <Col xs="2"></Col>
        <Col md="6">
          <Row>
            <h1>{user.fullName}</h1>
          </Row>
          <Row>
            <p>{user.bio}</p>
          </Row>
        </Col>
      </Row>
      <br></br> <br></br>
    </Container>
  );
};

export default UserDetails;
