--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: luxoftTest; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "luxoftTest" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Ukrainian_Ukraine.1252' LC_CTYPE = 'Ukrainian_Ukraine.1252';


ALTER DATABASE "luxoftTest" OWNER TO postgres;

\connect "luxoftTest"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: node_params; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.node_params (
    node_id character varying(255) NOT NULL,
    param_value character varying(255),
    param_name character varying(255) NOT NULL
);


ALTER TABLE public.node_params OWNER TO postgres;

--
-- Name: nodes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nodes (
    id character varying(255) NOT NULL,
    description character varying(255),
    name character varying(255),
    type character varying(255),
    parent_id character varying(255)
);


ALTER TABLE public.nodes OWNER TO postgres;

--
-- Data for Name: node_params; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.node_params (node_id, param_value, param_name) FROM stdin;
\.


--
-- Data for Name: nodes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.nodes (id, description, name, type, parent_id) FROM stdin;
\.


--
-- Name: node_params node_params_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.node_params
    ADD CONSTRAINT node_params_pkey PRIMARY KEY (node_id, param_name);


--
-- Name: nodes nodes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nodes
    ADD CONSTRAINT nodes_pkey PRIMARY KEY (id);


--
-- Name: node_params fk4ueirdlddkghybokw8txajvog; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.node_params
    ADD CONSTRAINT fk4ueirdlddkghybokw8txajvog FOREIGN KEY (node_id) REFERENCES public.nodes(id);


--
-- Name: nodes fkml83dqkjygb0d97yn62yn1so8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nodes
    ADD CONSTRAINT fkml83dqkjygb0d97yn62yn1so8 FOREIGN KEY (parent_id) REFERENCES public.nodes(id);


--
-- PostgreSQL database dump complete
--

