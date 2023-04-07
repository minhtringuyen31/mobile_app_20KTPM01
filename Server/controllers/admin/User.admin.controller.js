import UserServices from '../../services/User.service.js';

const UserAdminController = {
  create: async (req, res) => {
    const {
      name,
      gender,
      email,
      phone,
      password,
      date_of_birth,
      address,
      avatar,
      role,
      is_disable,
    } = req.body;
    console.log(req.body);
    const newUser = await UserServices.create(
      name,
      gender,
      email,
      phone,
      password,
      date_of_birth,
      address,
      avatar,
      role,
      is_disable
    );
    if (newUser) {
      res.status(200).send(req.body);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  update: async (req, res) => {
    const id = req.params.id;
    const {
      name,
      gender,
      email,
      phone,
      password,
      date_of_birth,
      address,
      avatar,
      role,
      is_disable,
    } = req.body;
    const userUpdate = await UserServices.update(
      id,
      name,
      gender,
      email,
      phone,
      password,
      date_of_birth,
      address,
      avatar,
      role,
      is_disable
    );
    if (userUpdate) {
      res.status(200).send(userUpdate);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  delete: async (req, res) => {
    const id = req.params.id;
    const status = await UserServices.delete(id);
    if (status) {
      res.status(200).send(status);
    } else {
      res.status(404).send(status);
    }
  },
  findOne: async (req, res) => {
    const id = req.params.id;
    const user = await UserServices.findOne(id);
    if (user) {
      res.status(200).send(user);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAll: async (req, res) => {
    const users = await UserServices.findAll();
    if (users) {
      res.status(200).send(users);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default UserAdminController;