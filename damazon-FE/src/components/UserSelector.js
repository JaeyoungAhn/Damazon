import React, {useEffect, useState} from 'react';
import axios from 'axios';

const UserSelector = ({onUserSelect}) => {
    const [users, setUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState();

    useEffect(() => {
        axios.get('http://localhost:8080/api/customers')
            .then((response) => setUsers(response.data))
            .catch((error) => console.error('Error fetching users:', error));
    }, []);

    const handleChange = (e) => {
        const selectedUserId = Number(e.target.value);
        const selectedUser = users.find((user) => user.id === selectedUserId);
        setSelectedUser(selectedUser);
        onUserSelect(selectedUser);
    };

    return (
        <div style={{fontSize: '18px'}}>
            <select style={{fontSize: '18px', height: '30px', marginTop: '20px'}} value={selectedUser?.id || ''}
                    onChange={handleChange}>
                <option value=''>Select a user</option>
                {users.map((user) => (
                    <option key={user.id} value={user.id}>{user.name}</option>
                ))}
            </select>
            {selectedUser && (
                <div style={{
                    backgroundColor: '#F0F8FF',
                    border: '1px solid #333',
                    borderRadius: '5px',
                    marginTop: '20px',
                    padding: '10px',
                }}>
                    <p>Email: {selectedUser.email}</p>
                    <p>Address: {selectedUser.address}</p>
                </div>
            )}
        </div>
    );
};

export default UserSelector;
