import UserRepository from '../repositories/User.repository.js';

import admin from 'firebase-admin';
import { initializeApp } from 'firebase-admin/app';

import serviceAccount from '../configs/serviceAccountKey.json' assert { type: 'json' };

const UserServices = {
  async countUser() {
    return await UserRepository.countUser();
  },
  async changeIsDisable(id, is_disable) {
    return await UserRepository.changeIsDisable(id, is_disable);
  },
  async create(
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
  ) {
    return await UserRepository.create(
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
  },
  async update(
    id,
    name,
    gender,
    date_of_birth,
    password,
    email,
    phone,
    address,
    is_disable,
    avatar,
    role
  ) {
    return await UserRepository.update(
      id,
      name,
      gender,
      date_of_birth,
      password,
      email,
      phone,
      address,
      is_disable,
      avatar,
      role
    );
  },
  async delete(id) {
    return await UserRepository.delete(id);
  },
  async saveTokenFirebase(user_id, token) {
    return await UserRepository.saveTokenFireBase(user_id, token);
  },
  async findOne(id) {
    return await UserRepository.findOneByID(id);
  },
  async findAll() {
    return await UserRepository.findAll();
  },
  // Cần method gì thì tự implements !!
  //
  async signup(email, password) {
    return await UserRepository.signup(email, password);
  },
  async findEmail(email) {
    return await UserRepository.findOneByEmail(email);
  },
  async findPhone(phone) {
    return await UserRepository.findOneByPhone(phone);
  },
  async setOTP(email, otp) {
    return await UserRepository.setOTP(email, otp);
  },
  async setPass(email) {
    return await UserRepository.setPass(email);
  },
  async getPass(email) {
    return await UserRepository.getPass(email);
  },
  async checkOTP(email, otp) {
    return await UserRepository.checkOTP(email, otp);
  },
  async changepass(id, password) {
    return await UserRepository.changepass(id, password);
  },
  async finAllToken() {
    return await UserRepository.findAllTokenFireBase();
  },
  async findOneToken(id) {
    return await UserRepository.findOneTokenFireBase(id);
  },
  async editprofile(id, name, email, gender, date_of_birth, address) {
    return await UserRepository.editprofile(
      id,
      name,
      email,
      gender,
      date_of_birth,
      address
    );
  },
  async findTokenByUserID(user_id) {
    try {
      var result = await UserRepository.findOneTokenByUserID(user_id);
      return result;
    } catch (error) {
      return false;
    }
  },
  async updateTokenFireBase(token, user_id) {
    try {
      var result = await UserRepository.updateTokenFireBase(token, user_id);
      return result;
    } catch (error) {
      return false;
    }
  },

  async handleTokenFireBase(userid, message, target) {
    if (!admin.apps.length) {
      admin.initializeApp({
        credential: admin.credential.cert(serviceAccount),
      });
    }
    // admin.initializeApp({
    //     credential: admin.credential.cert(serviceAccount)
    // });
    if (target === 'all') {
      const registrationTokens = await this.finAllToken();
      const messageHandle = {
        notification: message,
        tokens: registrationTokens, // danh sách các token thiết bị nhận thông báo
      };
      var result = admin.messaging().sendMulticast(messageHandle);
    } else {
      const registrationToken = await this.findTokenByUserID(userid);
      console.log(registrationToken);
      const messageOne = message;
      admin.messaging().sendToDevice(registrationToken.token, messageOne);
    }
  },
};

export default UserServices;
