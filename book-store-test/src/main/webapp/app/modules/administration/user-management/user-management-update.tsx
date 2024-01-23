import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { ValidatedField, ValidatedForm, isEmail } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getUser, getRoles, updateUser, createUser, reset } from './user-management.reducer';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UserManagementUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { login } = useParams<'login'>();
  const isNew = login === undefined;

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getUser(login));
    }
    dispatch(getRoles());
    return () => {
      dispatch(reset());
    };
  }, [login]);

  const handleClose = () => {
    navigate('/admin/user-management');
  };

  const saveUser = values => {
    if (isNew) {
      dispatch(createUser(values));
    } else {
      dispatch(updateUser(values));
    }
    handleClose();
  };

  const isInvalid = false;
  const user = useAppSelector(state => state.userManagement.user);
  const loading = useAppSelector(state => state.userManagement.loading);
  const updating = useAppSelector(state => state.userManagement.updating);
  const authorities = useAppSelector(state => state.userManagement.authorities);

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h1>Создать или отредактировать пользователя</h1>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm onSubmit={saveUser} defaultValues={user}>
              {user.id ? <ValidatedField type="text" name="id" required readOnly label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                type="text"
                name="login"
                label="Логин"
                validate={{
                  required: {
                    value: true,
                    message: 'Необходимо ввести логин.',
                  },
                  pattern: {
                    value: /^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$/,
                    message: 'Ваше имя пользователя недействительно.',
                  },
                  minLength: {
                    value: 1,
                    message: 'Ваш логин должен содержать хотя бы 1 символ',
                  },
                  maxLength: {
                    value: 50,
                    message: 'Ваш логин не может быть длинее чем 50 символов',
                  },
                }}
              />
              <ValidatedField
                type="text"
                name="firstName"
                label="Имя"
                validate={{
                  maxLength: {
                    value: 50,
                    message: 'Это поле не может быть длинее, чем 50 символов.',
                  },
                }}
              />
              <ValidatedField
                type="text"
                name="lastName"
                label="Фамилия"
                validate={{
                  maxLength: {
                    value: 50,
                    message: 'Это поле не может быть длинее, чем 50 символов.',
                  },
                }}
              />
              <FormText>This field cannot be longer than 50 characters.</FormText>
              <ValidatedField
                name="email"
                label="Эл. почта"
                placeholder="Ваша эл. почта"
                type="email"
                validate={{
                  required: {
                    value: true,
                    message: 'Email должен быть указан.',
                  },
                  minLength: {
                    value: 5,
                    message: 'Длина email должна быть хотя-бы 5 символов',
                  },
                  maxLength: {
                    value: 254,
                    message: 'Email не может быть длиннее чем 50 символов',
                  },
                  validate: v => isEmail(v) || 'Email не верен.',
                }}
              />
              <ValidatedField type="checkbox" name="activated" check value={true} disabled={!user.id} label="Активен" />
              <ValidatedField type="select" name="authorities" multiple label="Профили">
                {authorities.map(role => (
                  <option value={role} key={role}>
                    {role}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} to="/admin/user-management" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Назад</span>
              </Button>
              &nbsp;
              <Button color="primary" type="submit" disabled={isInvalid || updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Сохранить
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default UserManagementUpdate;
