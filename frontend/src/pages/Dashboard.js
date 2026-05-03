import React, { useEffect, useState } from "react";
import { addCropRecord, createFarmAccount, getFarmAccounts, getCropRecords, getProfit } from "../api/farmApi";

export default function Dashboard() {
  const [accounts, setAccounts] = useState([]);
  const [selected, setSelected] = useState(null);
  const [records, setRecords] = useState([]);
  const [profit, setProfit] = useState(0);

  const [accountForm, setAccountForm] = useState({ farmerName: "", email: "", farmName: "", location: "" });
  const [recordForm, setRecordForm] = useState({ cropName: "", expense: "", revenue: "", recordDate: "" });

  const loadAccounts = async () => setAccounts((await getFarmAccounts()).data);

  const loadAccountData = async (id) => {
    setSelected(id);
    setRecords((await getCropRecords(id)).data);
    setProfit((await getProfit(id)).data.totalProfit);
  };

  useEffect(() => { loadAccounts(); }, []);

  return (
    <div className="container">
      <h1>Farm Management</h1>

      <section>
        <h2>Create Farm Account</h2>
        <input placeholder="Farmer Name" onChange={(e) => setAccountForm({ ...accountForm, farmerName: e.target.value })} />
        <input placeholder="Email" onChange={(e) => setAccountForm({ ...accountForm, email: e.target.value })} />
        <input placeholder="Farm Name" onChange={(e) => setAccountForm({ ...accountForm, farmName: e.target.value })} />
        <input placeholder="Location" onChange={(e) => setAccountForm({ ...accountForm, location: e.target.value })} />
        <button onClick={async () => { await createFarmAccount(accountForm); await loadAccounts(); }}>Create</button>
      </section>

      <section>
        <h2>Farm Accounts</h2>
        {accounts.map((a) => (
          <button key={a.id} onClick={() => loadAccountData(a.id)}>{a.farmName || a.farmerName}</button>
        ))}
      </section>

      {selected && (
        <section>
          <h2>Add Crop Expense/Revenue</h2>
          <input placeholder="Crop" onChange={(e) => setRecordForm({ ...recordForm, cropName: e.target.value })} />
          <input placeholder="Expense" type="number" onChange={(e) => setRecordForm({ ...recordForm, expense: Number(e.target.value) })} />
          <input placeholder="Revenue" type="number" onChange={(e) => setRecordForm({ ...recordForm, revenue: Number(e.target.value) })} />
          <input type="date" onChange={(e) => setRecordForm({ ...recordForm, recordDate: e.target.value })} />
          <button onClick={async () => { await addCropRecord(selected, recordForm); await loadAccountData(selected); }}>Add Record</button>

          <h3>Total Profit: ${profit}</h3>
          <ul>
            {records.map((r) => (
              <li key={r.id}>{r.cropName}: Expense ${r.expense} | Revenue ${r.revenue}</li>
            ))}
          </ul>
        </section>
      )}
    </div>
  );
}
