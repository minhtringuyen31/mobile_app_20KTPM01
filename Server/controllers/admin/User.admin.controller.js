import UserServices from '../../services/User.service.js';

const UserAdminController = {
  countUser: async (req, res) => {
    const countUser = await UserServices.countUser();
    if (countUser) {
      res.status(200).send({ count: countUser });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  disable: async (req, res) => {
    const userDisable = await UserServices.changeIsDisable(req.params.id, 1);
    if (userDisable) {
      res.status(200).send(userDisable);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  enable: async (req, res) => {
    const userEnable = await UserServices.changeIsDisable(req.params.id, 0);
    if (userEnable) {
      res.status(200).send(userEnable);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
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
